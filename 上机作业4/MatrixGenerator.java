import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MatrixGenerator {
    private double[][] matrix1;
    private double[][] matrix2;
    private double[][] result1;
    private double[][] result2;
    private long time_LBL;
    private AtomicInteger count = new AtomicInteger(0);
    private int row1;
    private int line1;
    private int row2;
    private MyThread[] thread;

    public MatrixGenerator(int numberMax, int Maximum)
    {
        Random random = new Random();
        row1 = random.nextInt(Maximum)+1;
        line1 = random.nextInt(Maximum)+1;
        row2 = random.nextInt(Maximum)+1;
        matrix1 = new double[line1][row1];
        matrix2 = new double[row1][row2];
        for(int i = 0; i < line1; i++)
        {
            for(int j = 0; j < row1; j++)
            {
                BigDecimal b = BigDecimal.valueOf(random.nextDouble() * numberMax);
                matrix1[i][j] = b.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }

        }

        for(int i = 0; i < row1; i++)
        {
            for(int j = 0; j < row2; j++)
            {
                BigDecimal b = BigDecimal.valueOf(random.nextDouble() * numberMax);
                matrix2[i][j] = b.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
        }
    }

    public void printMatrix1() {
        System.out.println("Matrix_ONE [" + line1 + "][" + row1 + "] ->");
        for(int i = 0; i < line1; i++)
        {
            for (int j = 0; j < row1; j++)
            {
                System.out.print(matrix1[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printMatrix2()
    {
        System.out.println("Matrix_TWO [" + row1 + "][" + row2 + "] ->");
        for(int i = 0; i < row1; i++)
        {
            for (int j = 0; j < row2; j++)
            {
                System.out.print(matrix2[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printResultMatirx1()
    {
        System.out.println("Matrix_RESULT [" + line1 + "][" + row2 + "] ->");
        for(int i = 0; i < line1; i++)
        {
            for (int j = 0; j < row2; j++)
            {
                System.out.print(new BigDecimal(result1[i][j]).setScale(2, RoundingMode.HALF_UP) + " ");
            }
            System.out.println();
        }

    }

    public void printResultMatirx2()
    {
        while(true)
        {
            if(count.get() == 2)
            {
                System.out.println("已经有" + count + "个线程完成计算");
                System.out.println("Matrix_RESULT [" + line1 + "][" + row2 + "] ->");
                for(int i = 0; i < line1; i++)
                {
                    for (int j = 0; j < row2; j++)
                    {
                        System.out.print(new BigDecimal(result2[i][j]).setScale(2, RoundingMode.HALF_UP) + " ");
                    }
                    System.out.println();
                }
                break;
            }
        }
    }

    public double[][] calculate_LBL()
    {
        result1 = new double[line1][row2];
        long from = System.nanoTime();
        for(int i = 0; i < line1; i++)
        {
            for(int j = 0; j < row2; j++)
            {
                result1[i][j] = 0.0;
                for(int k = 0; k < row1; k++)
                    result1[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        }
        time_LBL = System.nanoTime() - from;
        return result1;
    }

    public void calculate_BTH(int Lines_Per_Thread) throws InterruptedException {
        assert !(Lines_Per_Thread==0);
        result2 = new double[line1][row2];
        int number_of_threads = line1/Lines_Per_Thread;
        thread = new MyThread[number_of_threads];
        int from = 0;
        for(int i = 0; i < number_of_threads; i++, from+=Lines_Per_Thread) {
            if(i == number_of_threads-1)
                thread[i] = new MyThread(from, row1, row2, matrix1, matrix2, result2, line1-from);
            else
                thread[i] = new MyThread(from, row1, row2, matrix1, matrix2, result2, Lines_Per_Thread);
        }
        long time = System.nanoTime();
        for(int i = 0; i < number_of_threads; i++)
        {
            thread[i].start();
        }
        for(int i = 0; i < number_of_threads; i++)
        {
            thread[i].join();
//            System.out.println("线程活跃数量"+Thread.activeCount());
        }

        long time2 = System.nanoTime()-time;
        System.out.println("Parallel calculating(" + Lines_Per_Thread + " lines/thread): " + time2 + "ns");
    }

    public long getTime_LBL() {
        return time_LBL;
    }

    public int getLine1() {
        return line1;
    }

    public int getRow1() {
        return row1;
    }

    public int getRow2() {
        return row2;
    }

    public boolean is_correct()
    {
        for(int i = 0 ; i< line1; i++)
        {
            for(int j = 0; j < row2; j++)
            {
                if(result1[i][j] != result2[i][j])
                    return false;

            }
        }
        return true;
    }
}
