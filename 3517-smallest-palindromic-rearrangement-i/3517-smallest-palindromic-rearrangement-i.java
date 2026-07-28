class Solution {
    public String smallestPalindrome(String s) {
        int freq[]= new int[26];
        for(char ch : s.toCharArray()){
            freq[ch-'a']++;
        }
        StringBuilder left = new StringBuilder();
        String middle="";
        for(int i=0;i<26;i++){
            left.append(String.valueOf((char) ('a' + i)).repeat(freq[i] / 2));
            if (freq[i] % 2 == 1) {
                middle = String.valueOf((char) ('a' + i));
            }
        }
         return left + middle + left.reverse().toString();
    }
}