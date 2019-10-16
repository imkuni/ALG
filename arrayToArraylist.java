int[][] first = new int[rowsInA][columnsInA];
int[][] second = new int[columnsInA][columnsInB];
//Matrix A
System.out.println("Enter matrix A");
for (int i = 0; i < first.length; i++) {
    for (int j = 0; j < first[0].length; j++) {
        first[i][j] = s.nextInt();
    }
}
//Matrix B
System.out.println("Enter matrix B");
for (int i = 0; i < second.length; i++) {
    for (int j = 0; j < second[0].length; j++) {
        second[i][j] = s.nextInt();
    }
}

ArrayList<ArrayList<Integer>> twoDimArrayListFirst = arrayToArraylist(first);
ArrayList<ArrayList<Integer>> twoDimArrayListSecond = arrayToArraylist(second);

private static  ArrayList<ArrayList<Integer>> arrayToArraylist(int[][] array){
    ArrayList<ArrayList<Integer>> twoDimArrayList = new ArrayList<ArrayList<Integer>>();
    for (int i = 0; i < array[0].length; i++) {
        ArrayList<Integer> intArrayList = new ArrayList<Integer>();
        for (int var : array[i]) {
            intArrayList.add(var);
        }
        twoDimArrayList.add(intArrayList);
    }
    return twoDimArrayList;
}
