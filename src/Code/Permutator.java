package Code;

import java.util.Arrays;
import java.util.Iterator;

// generates permutations of an array
// stackoverflow.com

public class Permutator<E> implements Iterator<E[]>{

    E[] arr1 = null;
    E[] arr2 = null;
    int size;
    int[] stack = null;

    int index = 0;
    public Permutator( E[] arr ){

        if( arr.length > 0 ){
            arr1 = arr;

            size = arr1.length;
            arr2 = Arrays.copyOf(arr1, size);

            stack = new int[size];
            Arrays.fill(stack, 0);
        }
    }

    @Override
    public boolean hasNext() {
        return (null != arr1 && arr1.length > 0);
    }

    @Override
    public E[] next() {

        // start computing.
        // We will return original array as value of last permutation.
        // This is to make "hasNext() " implementation easy.
        updateValue();
        return arr2;
    }

    protected void updateValue(){

        boolean bret = false;

        for( ; index < size ; ){

            if( stack[index] < index ){

                if( index %2 == 0 ){
                    swap(0, index);
                }else{
                    swap(stack[index], index);
                }

                stack[index]++;
                index = 0;
                bret = true;
                break;
            }else{
                stack[index] = 0;
                index++;
            }
        }

        if( !bret ){
            // No more permutation available.
            // Set the original array as return value.
            // Also set arr1 = null , so that hasNext() will return false for next test
            arr2 = arr1;
            arr1 = null;
        }
    }

    private void swap (final int i, final int j) {
        E temp = arr2[i];
        arr2[i] = arr2 [j];
        arr2[j] = temp;
    }
}