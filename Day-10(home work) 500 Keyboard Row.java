class Solution {
    public String[] findWords(String[] words) {
        String row1 = "qwertyuiop";
        String row2 = "asdfghjkl";
        String row3 = "zxcvbnm";

        List<String> ans = new ArrayList<>();

        for (String word : words) {
            String w = word.toLowerCase();

            String row;
            if (row1.indexOf(w.charAt(0)) != -1)
                row = row1;
            else if (row2.indexOf(w.charAt(0)) != -1)
                row = row2;
            else
                row = row3;

            boolean ok = true;

            for (char c : w.toCharArray()) {
                if (row.indexOf(c) == -1) {
                    ok = false;
                    break;
                }
            }

            if (ok)
                ans.add(word);
        }

        return ans.toArray(new String[0]);
    }
}
