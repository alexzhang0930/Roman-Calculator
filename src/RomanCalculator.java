import java.util.Scanner;

public class RomanCalculator {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Input Expression: ");
            String input = scanner.nextLine();
            if(input.equals("q")) break;
            System.out.println(romCalc(input));
        }
        scanner.close();
    }
    public static String romCalc(String eq) {
        String[] arr = eq.split(" ");
        LinkedListStack<String> operators = new LinkedListStack<>();
        LinkedListStack<Integer> values = new LinkedListStack<>();

        for(String str : arr) {
            if(str.equals("(")) {}
            
            else if(str.equals("additur") || 
                    str.equals("demitur") || 
                    str.equals("multiplicatur") || 
                    str.equals("dividitur")) {

                    operators.push(str);
                    
            }
            else if(str.equals(")")) {

                String op = operators.pop();
                double v = values.pop();

                if(op.equals("additur")) {
                    v = values.pop() + v;
                }
                else if(op.equals("demitur")) {
                    v = values.pop() - v;
                }
                else if(op.equals("multiplicatur")) {
                    v = values.pop() * v;
                }
                else if(op.equals("dividitur")) {
                    v = values.pop() / v;
                }
                values.push((int) v);
            }
                

            else {
                values.push(romToDec(str));
            }
        }
        return decToRom(values.pop());
    }
    private static int convertOp(String op) {
        if(op.equals("additur") || op.equals("demitur")) {
            return 0;
        }
        else {
            return 1;
        }
    }

    public static String decToRom(int num) {
        String rom = "";

        int[] arr = new int[(int) Math.log10(num) + 1];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = num % (int) (Math.pow(10, i + 1)) / (int) (Math.pow(10, i));
        }

        String[][] key = {
            {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
            {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
            {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
            {"", "M", "MM", "MMM"}
        };

        for(int i = arr.length - 1; i > -1; i--) {
            rom += key[i][arr[i]];
        }

        return rom;
    }

    public static int romToDec(String rom) {
        int n = 0;

        for(int i = 0; i < rom.length(); i++) {
            int temp = value(rom.charAt(i));
            if(i < rom.length() - 1 ) {
                int next = value(rom.charAt(i + 1));
                if(temp < next) {
                    n += next - temp;
                    i++;
                }
                else {
                    n += temp;
                }
            }
            else {
                n += temp;
            }
        }

        return n;
    }
    private static int value(char r) {
        switch (r) {
            case 'I':
                return 1;
            case 'V': 
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
