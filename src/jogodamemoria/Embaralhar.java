package jogodamemoria;

import java.util.Random;

public class Embaralhar {

    public static void embaralhar(Object[] a) {
        Random rnd = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            int randomNumber = rnd.nextInt(i + 1);
            swap(a, i, randomNumber);
        }
    }

    private static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
