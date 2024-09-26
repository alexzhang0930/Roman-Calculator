public class RomanCalculator {
    public static void main(String[] args) throws Exception {
        //System.out.println(romCalc("( V additur V )"));
        //LinkedListStack<String> test = new LinkedListStack<>();
        //test.push("head");
        romCalc("( I additur I )");
        //System.out.println("test");
        //System.out.flush();
    }
    public static String romCalc(String eq) {
        String[] arr = eq.split(" ");
        LinkedListStack<String> operators = new LinkedListStack<>();
        LinkedListStack<Integer> values = new LinkedListStack<>();

        for(String str : arr) {
            //System.out.println(str);
            if(str.equals("(")) {
                operators.push(str);
            }
            else if(str.equals("additur") || 
                    str.equals("demitur") || 
                    str.equals("multiplicatur") || 
                    str.equals("dividitur")) {

                    while(!operators.isEmpty() && convertOp(operators.peak()) >= convertOp(str)) {
                        String op = operators.pop();

                        if(op.equals("additur")) {
                            values.push(values.pop() + values.pop());
                        }
                        else if(op.equals("demitur")) {
                            values.push(-1 * values.pop() + values.pop());
                        }
                        else if(op.equals("multiplicatur")) {
                            values.push(values.pop() * values.pop());
                        }
                        else if(op.equals("dividitur")) {
                            values.push((int) ((1.0 / (double) values.pop()) * values.pop()));
                        }
                    }

                    operators.push(str);
                    
            }
            else if(str.equals(")")) {
                while(!operators.peak().equals("(")) {
                    String op = operators.pop();

                    if(op.equals("additur")) {
                        values.push(values.pop() + values.pop());
                    }
                    else if(op.equals("demitur")) {
                         values.push((-1 * values.pop()) + values.pop());
                    }
                    else if(op.equals("multiplicatur")) {
                        values.push(values.pop() * values.pop());
                    }
                    else if(op.equals("dividitur")) {
                        values.push((int) ((1.0 / (double) values.pop()) * values.pop()));
                    }
                }
                operators.pop();
            }
            else {
                values.push(romToDec(str));
            }
        }
        while(!operators.isEmpty()) {
            String op = operators.pop();

            if(op.equals("additur")) {
                values.push(values.pop() + values.pop());
            }
            else if(op.equals("demitur")) {
                 values.push(-1 * values.pop() + values.pop());
            }
            else if(op.equals("multiplicatur")) {
                values.push(values.pop() * values.pop());
            }
            else if(op.equals("dividitur")) {
                values.push((int) ((1.0 / (double) values.pop()) * values.pop()));
            }
        }

        System.out.println("test");
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
