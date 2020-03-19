package Lesson5;

public class Lesson5 {

    private final static int size = 10000000;
    private final static int h = size / 2;
    private static float[] arr = new float[size];

    public static void main(String[] args) {
        mSingle();
        try {
            mDouble();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void mSingle() {
        setAll1();
        long a = System.currentTimeMillis();
        calcArray(arr);
        System.out.println("Single - " + (System.currentTimeMillis() - a));
    }

    private static void mDouble() throws InterruptedException {
        setAll1();
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);
        Thread t0 = new Thread(() -> calcArray(arr1));
        Thread t1 = new Thread(() -> calcArray(arr2));
        t0.start();
        t1.start();
        //We need wait, while threads working.
        t0.join();
        t1.join();
        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        System.out.println("Double - " + (System.currentTimeMillis() - a));
    }

    private static void setAll1() {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
    }

    private static void calcArray(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}