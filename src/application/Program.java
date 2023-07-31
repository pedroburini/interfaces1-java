package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("entre com os dados do aluguel: ");
		System.out.print("modelo do carro: ");
		String carModel = sc.nextLine();
		System.out.print("retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		System.out.print("retorno (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.print("entre com o preço por hora: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("entre com o preço por dia: ");
		double pricePerDay = sc.nextDouble();

		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println("fatura: ");
		System.out.println("pagamento básico: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("imposto: " + String.format("%.2f",cr.getInvoice().getTax()));
		System.out.println("pagamento total: " + String.format("%.2f",cr.getInvoice().getTotalPayment()));
		
		sc.close();
	}
}
