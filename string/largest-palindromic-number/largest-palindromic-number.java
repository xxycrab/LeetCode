class Solution {
    public String largestPalindromic(String num) {

        int[] occurrence = new int[10];
        for(char d : num.toCharArray()) {
            occurrence[d-'0']++;
        }
        int[] single = new int[10];
        int maxSingle = 0;

        StringBuilder sb = new StringBuilder();
        for(int i = 9; i >=0; i--) {
            int count = occurrence[i];
            while(occurrence[i] > (count+1) / 2) {
                occurrence[i]--;
                sb.append(String.valueOf(i));
            }
            if (occurrence[i] > count / 2) {
                occurrence[i]--;
                maxSingle = Math.max(i, maxSingle);
                single[i] = 1;
            } 
        }
        if (sb.length() == 0 || sb.charAt(0) == '0') {
            return String.valueOf(maxSingle);
        } else {
            sb.append(String.valueOf(maxSingle));
            for(int i = 0; i<=9; i++) {
                while(occurrence[i] > 0) {
                    occurrence[i]--;
                    sb.append(String.valueOf(i));
                }
            }
        }
        return sb.toString();
        
    }
}