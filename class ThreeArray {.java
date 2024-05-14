class GFG {
	public static void main(String[] args)
	{
        int[][] array2d = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
		int[][][] array3d = { { { 1, 2 }, { 3, 4 } }, { { 5, 6 }, { 7, 8 } } };
        String[][][][] array4d = {{{{"1" , "2"}, {"1", "2"}}, {{"4", "5"}, {"6", "7"}}}, {{{"8" , "9"}, {"10", "11"}}, {{"12", "13"}, {"14", "15"}}}};
        System.out.println(array2d[0][0]);
		System.out.println(array3d[0][0][1]);
        int i =Integer.parseInt(array4d[0][0][0][0]);  
        System.out.println(i);

	}
}
