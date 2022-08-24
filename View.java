import java.util.*;

public class View {
    private Scanner scanner;
    private Map<String, Account> dataBase = new HashMap<String, Account>();

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public void initAccount() {
        Account ac1 = new Account("default account 1", "123456", "defaultac1@gmail.com");
        Account ac2 = new Account("default account 2", "123456", "defaultac2@gmail.com");
        dataBase.put(ac1.getUserName(),ac1);
        dataBase.put(ac2.getUserName(),ac2);
    }

    public void display() {
        displayView();
        System.out.println("Ban muon dang nhap hay dang ky: ");
        int a = scanner.nextInt();
        scanner.nextLine();
        boolean isQuit = false;
        switch (a) {
            case 1:{ // Log in
                logIn();
                break;
            }
            case 2: { // Register
                register();
                break;
            }
            case 3: { // Quit
                isQuit = true;
                break;
            }
            default:
                break;
        }
        if (isQuit == true) {
            System.out.println("Hen gap lai");
        }
    }

    public void displayView() {
        System.out.println("Menu:");
        System.out.println("[1] Log in");
        System.out.println("[2] Register");
        System.out.println("[3] Quit");
    }


    //Đăng nhập
    public void logIn() {
        boolean isTrue = true;
        while(isTrue) {
            System.out.println("Xin hay nhap username:");
            String userName = scanner.nextLine();
            System.out.println("Xin hay nhap password:");
            String password = scanner.nextLine();
            Account u = dataBase.get(userName);
            if (u != null && u.getPassword().equals(password)) {
                isTrue = false;
                System.out.println("Dang nhap thanh cong, chao mung " + userName + ", ban co the thuc hien cac cong viec sau:");
                System.out.println("[1] Thay doi username");
                System.out.println("[2] Thay doi email");
                System.out.println("[3] Thay doi mat khau");
                System.out.println("[4] Dang xuat");
                System.out.println("[0] Thoat chuong trinh");
                boolean t = true;
                while(t) {
                    System.out.println("Xin hay nhap viec ban muon lam:");
                    int c = scanner.nextInt();
                    scanner.nextLine();
                    switch(c) {
                        case 0: {
                            System.out.println("Hen gap lai");
                            t = false;
                            break;
                        }
                        case 1: {
                            System.out.println("Xin hay nhap username moi:");
                            u.setUserName(scanner.nextLine());
                            System.out.println("Thong tin tai khoan sau khi cap nhat user: " + u.toString());
                            System.out.println(dataBase);
                            break;
                        }
                        case 2: {
                            System.out.println("Xin hay nhap email moi:");
                            u.setEmail(scanner.nextLine());
                            System.out.println("Thong tin tai khoan sau khi cap nhat email: " + u.toString());
                            break;
                        }
                        case 3: {
                            System.out.println("Xin hay nhap password moi:");
                            u.setPassword(scanner.nextLine());
                            System.out.println("Thong tin tai khoan sau khi cap nhat password: " + u.toString());
                            break;
                        }
                        case 4: {
                            t = false;
                            display();
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }             
            } else if (u == null || dataBase.containsKey(userName) == false) {
                System.out.println("Kiem tra lai username");
            } else {
                System.out.println("Mat khau ban nhap khong dung, chon 1 hoac 2");
                System.out.println("[1] Dang nhap lai");
                System.out.println("[2] Quen mat khau");
                int a = scanner.nextInt();
                switch(a) {
                    case 1: {
                        scanner.nextLine();
                        break;
                    }
                    case 2: {
                        isTrue = false;
                        scanner.nextLine();
                        forgotPassword();
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }           
        } 
    }
    
    public void forgotPassword() {
        System.out.println("Xin hay nhap email:");
        String searchEmail = scanner.nextLine();
        Account u = dataBase.get(searchEmail);
            if (u != null) {
                System.out.println("Xin hay nhap mat khau moi:");
                String newPassword = scanner.nextLine();
                u.setPassword(newPassword);
                System.out.println("Doi mat khau thanh cong");
                logIn();
            } else {
                System.out.println("Tai khoan nay chua ton tai");
            }
    }


    // Đăng ký
    public void register() {
        boolean emailCheck, passwordCheck;
        System.out.println("Xin hay nhap username:");
        String userName = scanner.nextLine();
        for (Map.Entry<String, Account> acc : dataBase.entrySet()) {
            boolean istrue = true;
            while(istrue) {
                if (acc.getValue().getUserName().equals(userName)) {
                    System.out.println("Username nay da ton tai, vui long nhap username khac");
                    userName = scanner.nextLine();
                }
                else {
                    istrue = false;
                }
            }
        }
        System.out.println("Xin hay nhap email (email@address.com)");
        String email = scanner.nextLine();
        for (Map.Entry<String, Account> acc : dataBase.entrySet()) {
            boolean istrue = true;
            while(istrue) {
                if (acc.getValue().getEmail().equals(email)) {
                    System.out.println("Email nay da ton tai, vui long nhap email khac");
                    email = scanner.nextLine();
                } else {
                    istrue = false;
                }
            }
        }
        do {
            String EmailPattern = "[a-zA-Z0-9]{3,15}@[a-zA-Z0-9]{3,15}([.][a-zA-Z]{2,5}){1,2}";
            emailCheck = email.matches(EmailPattern);
            if (!emailCheck) {
                System.out.println("Email ko hop le, xin hay nhap lai email");
                email = scanner.nextLine();
            }
        } while (!emailCheck);
        System.out.println("Email hop le");
        System.out.println("Xin hay nhap password");
        String password = scanner.nextLine();
        do {
            String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[.,-_;]).{7,15}$";
            passwordCheck = password.matches(passwordPattern);
            if (!passwordCheck) {
                System.out.println("Password ko hop le, xin hay nhap lai password");
                password = scanner.nextLine();
            }
        } while (!passwordCheck);
        System.out.println("Password hop le");
        Account ac = new Account(userName, password, email);
        dataBase.put(ac.getUserName(), ac);
        System.out.println("Ban da dang ky tai khoan thanh cong");
        display();
    }
}

