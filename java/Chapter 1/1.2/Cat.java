public class Cat {
    public static void main(String[] args) { // Copy input files to out (last argument).
        Out out = new Out(args[args.length - 1]);
        for (int i = 0; i < args.length - 1; i++) { // Copy input file named on ith arg to out.
            In in = new In(args[i]);
            String s = in.readAll();
            out.println(s);
            in.close();
        }
        out.close();
    }
// % more in1.txt
// This is
// % more in2.txt
// a tiny
// test.
// % java Cat in1.txt in2.txt out.txt
// % more out.txt
// This is
// a tiny
// test.
}