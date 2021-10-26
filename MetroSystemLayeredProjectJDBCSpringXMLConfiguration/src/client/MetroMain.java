package client;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import presentation.MetroPresentation;


public class MetroMain {
	
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		ApplicationContext springContainer=new ClassPathXmlApplicationContext("metro.xml");
		MetroPresentation metroPresentation=(MetroPresentation)springContainer.getBean("metroPresentationImpl");
		
		while(true) {
			metroPresentation.showMenu();
			System.out.print("Enter Your choice: ");
			int choice=scanner.nextInt();
			metroPresentation.performMenu(choice);
		}
	}

}
