
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MatrixGenerator matrixGenerator = new MatrixGenerator(1000, 10);
        System.out.println("Matrix_ONE: LINE-> " + matrixGenerator.getLine1() +" | " + "ROW-> " + matrixGenerator.getRow1());
        System.out.println("Matrix_TWO: LINE-> " + matrixGenerator.getRow1() +" | " + "ROW-> " + matrixGenerator.getRow2());
//        matrixGenerator.printMatrix1();
//        matrixGenerator.printMatrix2();
        System.out.println("\r\n" + "----------------Start Calculating--------------" + "\r\n");
        matrixGenerator.calculate_LBL();
//        matrixGenerator.printResultMatirx1();
        System.out.println("Serial calculating: " + matrixGenerator.getTime_LBL() + "ns" + "\r\n");


//        matrixGenerator.calculate_LBL();
//        matrixGenerator.printResultMatirx1();
//        System.out.println("串行计算时间: " + matrixGenerator.getTime_LBL() + "纳秒" + "\r\n");
        matrixGenerator.calculate_BTH(1);
        matrixGenerator.calculate_BTH(4);
        System.out.println("\r\n" + "----------------Result Testing--------------" + "\r\n");
        System.out.println("VALIDITY：" + matrixGenerator.is_correct());
//        matrixGenerator.printResultMatirx2();
    }
}
