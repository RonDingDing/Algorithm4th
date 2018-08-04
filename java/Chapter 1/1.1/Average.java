public class Average {
    public static void main(String[] args) { // Average the numbers on StdIn.
        // 输入一个数之后按回车，完毕后按Ctrl-Z
        double sum = 0.0;
        int cnt = 0;
        while (!StdIn.isEmpty()) { // Read a number and cumulate the sum.
            sum += StdIn.readDouble();
            cnt++;
        }
        double avg = sum / cnt;
        StdOut.printf("Average is %.5f\n", avg);
    }
}