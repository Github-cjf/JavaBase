package com.arithmetic.strcuture.stackapp;

import com.arithmetic.strcuture.stacks.LinkStack;

import java.util.Scanner;

/*
 * ջ��ʵ�ִ��������      ����� ����
 */
public class StackBigInt extends LinkStack {
	private static LinkStack SA = new LinkStack();
	private static LinkStack SB = new LinkStack();
	private static LinkStack SC = new LinkStack();

	//������������
	private static void load(){
		String num1,num2;
		Scanner scanner = new Scanner(System.in);
        System.out.print("�����һ������:  ");
        num1 = scanner.nextLine();
        System.out.print("����ڶ�������:  ");
        num2 = scanner.nextLine();
		scanner.close();
		int i=0;
		while(i<num1.length()){
			SA.push(num1.charAt(i));//sharAt�ŵ����±�ֵ
			//System.out.println(a.charAt(i));
			i++;
		}
		i=0;
		while(i<num2.length()){
			SB.push(num2.charAt(i));
			//System.out.println(b.charAt(i));
			i++;
		}
	}
	public static void main(String[] args) {
		int flag=0;
		System.out.println("������������������������λ������");
		load();

		while(!SA.isEmpty() && !SB.isEmpty()){
			int sum;
			sum = SA.pop()+SB.pop()+flag-96;flag = 0;
			if (sum>=10) { sum-=10;flag = 1;}
			SC.push(sum);
		}
		if (!SA.isEmpty() && SB.isEmpty()){
			while (!SA.isEmpty()){
				int t = SA.pop()+flag-48;flag=0;
				if (t>=10) { t-=10;flag = 1;}
				SC.push(t);
			}
		}else if (SA.isEmpty() && !SB.isEmpty()){
			while (!SB.isEmpty()){
				int t = SB.pop()+flag-48;flag=0;
				if (t>=10) { t-=10;flag = 1;}
				SC.push(t);
			}
		}
		if (flag == 1)   SC.push(flag);
		System.out.println("�����������ĺ��ǣ�");
		SC.display();//�����Ľ��˳��պ������ֵ�˳��
	}

}
