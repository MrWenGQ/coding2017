package main.coding_170302;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		for(int i=0,j=origin.length-1;i<origin.length/2;i++,j--){
			int temp = origin[i];
			origin[i] = origin[j];
			origin[j] = temp;
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int size=0;//表示非零个数
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]!=0){
				oldArray[size] = oldArray[i];
				size++;
			}
		}
		return Arrays.copyOf(oldArray,size);
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int[] array3 = new int[array1.length+array2.length];
		int size=0;//表示array3元素个数
		int i=0,j=0;
		for(;i<array1.length&&j<array2.length;){
			if(array1[i]<array2[j]){
				array3[size++] = array1[i++];
			}else if(array1[i]==array2[j]){
				array3[size++] = array1[i++];
				j++;
			}else{
				array3[size++] = array2[j++];
			}
		}
		while (i<array1.length){
			array3[size++] = array1[i++];
		}
		while (j<array2.length){
			array3[size++] = array2[j++];
		}
		return  Arrays.copyOf(array3,size);
	}
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int [] oldArray,  int size){
		int[] newArray = new int[oldArray.length+size];
		System.arraycopy(oldArray,0,newArray,0,oldArray.length);
		return newArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		if(max<=1){
			return new int[0];
		}
		ArrayList list = new ArrayList();
		int fib1 = 1;
		int fib2 = 1;
		list.add(fib1);
		while (fib2<max){
			list.add(fib2);
			int temp = fib2;
			fib2 = fib1+ fib2;
			fib1 = temp;
		}
		int[] array = new int[list.size()];
		for(int i=0;i<list.size();i++){
			array[i] = (int)list.get(i);
		}
		return array;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if(max<=2)return new int[0];
		List list = new ArrayList();
		for(int i=2;i<max;i++){
			if(isPrime(i)){
				list.add(i);
			}
		}
		int[] primeArray = new int[list.size()];
		for(int i=0;i<list.size();i++){
			primeArray[i] = (int)list.get(i);
		}
		return primeArray;
	}
	public boolean isPrime(int element){
		if(element==1)return false;
		if(element==2||element==3)return true;
		for(int i=2;i<=Math.sqrt(element);i++){
			if(element%i==0)
				return false;
		}
		return true;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if(max<6){
			return new int[0];
		}
		List<Integer> list = new ArrayList<>();
		for(int i=6;i<=max;i++){
			if(isPerfectNumbers(i)){
				list.add(i);
			}
		}
		int[] array = new int[list.size()];
		for(int i=0;i<list.size();i++){
			array[i] = list.get(i);
		}
		return array;
	}

	public boolean isPerfectNumbers(int element){
		if(element==1||isPrime(element))
			return false;
		List<Integer> list = new ArrayList<>();
		int end =(int) Math.sqrt(element);//
		for(int i=2;i<=end;){
			if(element%i==0){
				list.add(i);
				element=element/i;
			}else{
				i++;
			}
		}
		int sum =1;
		for(Integer i:list){
			sum+=i;
		}
		if(element==sum){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<array.length;i++){
			sb.append(array[i]);
			sb.append(seperator);
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

}
