class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Map<String, Queue<String>> validMutations = new HashMap<>();
        validMutations.put(startGene, new LinkedList<String>());
        for (String g : bank) {
            if (validMutations.get(g) != null) {
                continue;
            }
            validMutations.put(g, new LinkedList<String>());
            if (this.isValidMutation(startGene, g)) {
                validMutations.get(g).add(startGene);
                validMutations.get(startGene).add(g);
            }
        }
        for (String g : validMutations.keySet()) {
            for (String h : validMutations.keySet()) {
                if (g.equals(h))
                    continue;
                if (this.isValidMutation(g, h)) {
                    validMutations.get(g).add(h);
                }
            }
        }

        Queue<String> q = new LinkedList<>();
        q.add(startGene);
        q.add("#");
        int length = 1;
        while (!q.isEmpty()) {
            String gene = q.poll();
            if (gene.equals("#")) {
                length++;
                continue;
            }
            while (!validMutations.get(gene).isEmpty()) {
                String g = validMutations.get(gene).poll();
                if (g.equals(endGene)) {
                    return length;
                } else {
                    q.add(g);
                }
            }
            q.add("#");
        }

        return -1;

    }

    private boolean isValidMutation(String startGene, String endGene) {
        if (startGene.equals(endGene)) {
            return false;
        }
        int diff = 0;
        for (int i = 0; i < startGene.length(); i++) {
            if (startGene.charAt(i) != endGene.charAt(i)) {
                diff++;
            }
            if (diff > 1) {
                return false;
            }
        }
        return true;
    }
}