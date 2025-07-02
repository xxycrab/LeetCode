class Solution {
    public String minWindow(String s, String t) {
        int end = 0;
        int minW = s.length();
        String res = "";
        Queue<Integer> caught = new LinkedList<>();

        Map<Character, Integer> charCount = new HashMap<>();
        Map<Character, Integer> charFound = new HashMap<>();
        for (char c : t.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        while (end < s.length()) {
            if (charCount.get(s.charAt(end)) != null) {
                charFound.put(s.charAt(end), charFound.getOrDefault(s.charAt(end), 0) + 1);
                caught.add(end);
            }

            if (covered(charCount, charFound)) {
                int firstCaught = caught.peek();
                while (!caught.isEmpty()
                        && charFound.getOrDefault(s.charAt(caught.peek()), 0) > charCount.getOrDefault(s.charAt(caught.peek()), 0)) {
                    firstCaught = caught.poll();
                    charFound.put(s.charAt(firstCaught), charFound.getOrDefault(s.charAt(firstCaught), 0) - 1);

                }
                firstCaught = caught.poll();

                if (end - firstCaught + 1 <= minW) {
                    res = s.substring(firstCaught, end + 1);
                    minW = Math.min(end - firstCaught + 1, minW);
                }
                charFound.put(s.charAt(firstCaught), charFound.getOrDefault(s.charAt(firstCaught), 0) - 1);
            }
            end++;
        }

        return res;

    }

    private boolean covered(Map<Character, Integer> charCount, Map<Character, Integer> charFound) {
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (charFound.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}