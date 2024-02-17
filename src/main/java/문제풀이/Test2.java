package 문제풀이;

public class Test2 {

    public static int changeAds(int base10){
        String binary = Integer.toBinaryString(base10);
        int startIndex = binary.indexOf("1");
        String substringOfBinary = binary.substring(startIndex);

        StringBuilder reversedBinaryStringBuilder = new StringBuilder();
        for(int i=0;i<substringOfBinary.length();i++){
            char reversedChar = substringOfBinary.charAt(i) == '1' ? '0' : '1';
            reversedBinaryStringBuilder.append(reversedChar);
        }

        return Integer.parseInt(reversedBinaryStringBuilder.toString(), 2);
    }


    public static void main(String[] args) {
        System.out.println(changeAds(30));
        System.out.println(changeAds(50));
    }
}
