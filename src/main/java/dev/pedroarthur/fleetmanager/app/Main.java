package dev.pedroarthur.fleetmanager.app;

import dev.pedroarthur.fleetmanager.model.*;
import dev.pedroarthur.fleetmanager.repository.DriverRepository;
import dev.pedroarthur.fleetmanager.repository.TripRepository;
import dev.pedroarthur.fleetmanager.repository.VehicleRepository;


import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String answer;

        //creating Repos to store information locally
        DriverRepository driverRepo = new DriverRepository();
        TripRepository tripRepo = new TripRepository();
        VehicleRepository vehicleRepo = new VehicleRepository();
        long idDriver = 1, idVehicle = 1, idTrip = 1;


        do {
            showMenu();
            answer = sc.nextLine();
            switch (answer) {
                case "1":
                    showMenuCreate();
                    String submenu = sc.nextLine();
                    switch (submenu) {
                        case "1":
                            try {
                                Driver driver = creatingDriverInput(idDriver, sc);
                                driverRepo.register(driver);
                                idDriver++;
                                System.out.println(driver.getName() + " cadastrado com sucesso!");
                                Thread.sleep(5000);
                            } catch (IllegalArgumentException | InterruptedException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "2":
                            try {
                                System.out.print("Digite o id do motorista (1, 2, 3): ");
                                long driverId = Long.parseLong(sc.nextLine());
                                System.out.print("Digite o id do veículo (1, 2, 3): ");
                                long vehicleId = Long.parseLong(sc.nextLine());
                                Driver driver = driverRepo.findById(driverId).orElseThrow(() ->
                                        new IllegalArgumentException("Motorista não encontrado!"));
                                Vehicle vehicle = vehicleRepo.findById(vehicleId).orElseThrow(() ->
                                        new IllegalArgumentException("Veículo não encontrado!"));
                                Trip trip = creatingTripInput(idTrip, driver, vehicle, sc);
                                tripRepo.register(trip);
                                idTrip++;
                                System.out.println("Viagem cadastrada com sucesso!");
                                Thread.sleep(5000);
                            } catch (IllegalArgumentException | InterruptedException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "3":
                            try {
                                Vehicle vehicle = creatingVehicleInput(idVehicle, sc); //asks the user to input vehicle values and stores it in vehicle
                                vehicleRepo.register(vehicle); //registers in the repository
                                idVehicle++; //id is incremented so the next vehicle gets a new id
                                System.out.println(vehicle.getModel() + " cadastrado com sucesso!");
                                Thread.sleep(5000);
                            } catch (IllegalArgumentException | InterruptedException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case "4":
                            //returns to previous menu
                            break;
                        case "0":
                            System.out.println("👋 Até logo! 👋");
                            break;
                        default:
                            invalidOptionReturn();
                    }
                    break;
                case "2":
                    showMenuRetrieve();
                    break;
                case "3":
                    showMenuUpdate();
                    break;
                case "4":
                    showMenuDelete();
                    break;
                case "0":
                    System.out.println("👋 Até logo! 👋");
                    break;

                default:
                    invalidOptionReturn();
                    break;
            }
        } while (!answer.equals("0")); //while answer differs from 0 the app won't close


        sc.close();
    }

    private static void showMenu() {
        System.out.println("\n\n=== 🚗🚒🚙🛻 Bem vindo(a) ao Fleet Manager 🚗🚒🚙🛻 ===\n\n");
        System.out.println("Por favor, escolha a opção desejada\n");
        System.out.println("1 - Menu de cadastros 📝\n");
        System.out.println("2 - Menu de visualização 🗒️\n");
        System.out.println("3 - Menu de atualização 🔄️\n");
        System.out.println("4 - Menu para deletar ❌\n");
        //System.out.println("5 - Relatório 📓\n");
        System.out.println("0 - Sair do Fleet Manager 👋\n");
        System.out.print("-> ");
    }

    private static void showMenuCreate() {
        System.out.println("Menu de cadastros 📝\n");
        System.out.println("Por favor, escolha a opção desejada\n");
        System.out.println("1 - Cadastrar motorista \n");
        System.out.println("2 - Cadastrar viagem ️\n");
        System.out.println("3 - Cadastrar veículo ️\n");
        System.out.println("4 - Voltar ao menu anterior \n");
        //System.out.println("5 - Relatório 📓\n");
        System.out.println("0 - Sair do Fleet Manager 👋\n");
        System.out.print("-> ");
    }

    private static void showMenuRetrieve() {
        System.out.println("Menu de relatório de dados existentes🗒️\n");
        System.out.println("Por favor, escolha a opção desejada\n");
        System.out.println("1 - Visualizar motoristas cadastrados \n");
        System.out.println("2 - Visualizar viagens cadastradas ️\n");
        System.out.println("3 - Visualizar veículos cadastrados 🔄️\n");
        System.out.println("4 - Voltar ao menu anterior ❌\n");
        //System.out.println("5 - Relatório 📓\n");
        System.out.println("0 - Sair do Fleet Manager 👋\n");
        System.out.print("-> ");
    }

    private static void showMenuUpdate() {
        System.out.println("Menu de atualizar dados existentes📝\n");
        System.out.println("Por favor, escolha a opção desejada\n");
        System.out.println("1 - Atualizar motoristas cadastrados \n");
        System.out.println("2 - Atualizar viagens cadastradas \n");
        System.out.println("3 - Atualizar veículos cadastrados \n");
        System.out.println("4 - Voltar ao menu anterior \n");
        //System.out.println("5 - Relatório 📓\n");
        System.out.println("0 - Sair do Fleet Manager 👋\n");
        System.out.print("-> ");
    }

    private static void showMenuDelete() {
        System.out.println("Menu de deletar dados ️❌\n");
        System.out.println("Por favor, escolha a opção desejada\n");
        System.out.println("1 - Deletar motorista cadastrado \n");
        System.out.println("2 - Deletar viagem cadastrada \n");
        System.out.println("3 - Deletar veículo cadastrado \n");
        System.out.println("4 - Voltar ao menu anterior \n");
        //System.out.println("5 - Relatório 📓\n");
        System.out.println("0 - Sair do Fleet Manager 👋\n");
        System.out.print("-> ");
    }

    private static void invalidOptionReturn() {
        try {
            System.out.println("❌ Opção inválida! Tente novamente! ❌\n");
            System.out.println("Você será redirecionado(a) ao menu em 5 segundos...");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.err.println("O temporizador falhou ou foi interrompido...");
        }
    }

    private static Driver creatingDriverInput(long id, Scanner sc) {
        System.out.print("Digite o nome do motorista: ");
        String name = sc.nextLine();
        System.out.print("Digite o numero da CNH do motorista: ");
        String licenseNumber = sc.nextLine();
        System.out.print("Categoria do motorista (B, C, D, E): ");
        String licenseCategoryInput = sc.nextLine();
        LicenseCategory licenseCategory = LicenseCategory.valueOf(licenseCategoryInput.toUpperCase());
        Driver driver = new Driver(id, name, licenseNumber, licenseCategory);
        return driver;
    }

    private static Trip creatingTripInput(long id, Driver driver, Vehicle vehicle, Scanner sc) {
        System.out.print("Digite a origem da viagem (São Paulo, Natal, etc): ");
        String origin = sc.nextLine();
        System.out.print("Digite o destino da viagem (São Bernardo do Campo, Belo Horizonte, etc): ");
        String destination = sc.nextLine();
        System.out.print("Digite a distância em Kilometros da viagem: ");
        double distanceKm = Double.parseDouble(sc.nextLine());
        Trip trip = new Trip(id, vehicle, driver, origin, destination, distanceKm);
        return trip;
    }

    private static Vehicle creatingVehicleInput(long id, Scanner sc) {
        System.out.print("Nome do veículo: ");
        String model = sc.nextLine();
        System.out.print("Placa do veículo: ");
        String plate = sc.nextLine();
        System.out.print("Ano de fabricação do veículo: ");
        int year = Integer.parseInt(sc.nextLine());
        System.out.print("Capacidade máxima em Kg: ");
        double capacity = Double.parseDouble(sc.nextLine());
        Vehicle vehicle = new Vehicle(id, plate, model, year, capacity);
        return vehicle;
    }

}