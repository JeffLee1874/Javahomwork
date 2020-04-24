public class MyThread extends Thread{
    int line1;
    int row1;
    int row2;
    double[][] matrix1;
    double[][] matrix2;
    double[][] result2;
    int line2;

    public MyThread(int line1, int row1, int row2, double[][] matrix1, double[][] matrix2, double[][] result2, int lines_Per_Thread)
    {
        this.line1 = line1;
        this.row1 = row1;
        this.row2 = row2;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.result2 = result2;
        this.line2 = line1 + lines_Per_Thread;
    }

    @Override
    public void run() {
//        System.out.println(Thread.currentThread().getName() + "开始的时间" + from);
//        from = System.currentTimeMillis();
//        for(int i = 0; i < line1; i++)
//        {
//                    if(i == line1/2)
//                        from = System.nanoTime();
            for(; line1 < line2; line1++)
            {
                result2[line1] = new double[row2];
                for(int j = 0; j < row2; j++)
                {
                    result2[line1][j] = 0.0;
                    for(int k = 0; k < row1; k++)
                        result2[line1][j] += matrix1[line1][k] * matrix2[k][j];
                }
            }
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(Thread.currentThread().getName() + "结束的时间" + end);
//        long gap2 = end - from;
//        System.out.println(Thread.currentThread().getName() + "运行时间" + gap2);
    }
}
