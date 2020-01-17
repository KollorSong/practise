public class ArrayMain {
    public static void main(String[] args) {
//        int[] arr = new int[20];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = i;
//        }
//        int[] scores = new int[]{100,95,20};
//        for (int i = 0; i < scores.length; i++) {
//            System.out.println(scores[i]);
//        }
//        for(int score : scores){
//            System.out.println(score);
//        }



        Array array = new Array(20);
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        array.insertElement(1,100);
        array.addBefore(-1);
        System.out.println(array);
        array.removeElement(2);
        System.out.println(array);
        array.removeFirst();
        System.out.println(array);

    }
}
