import javax.swing.JOptionPane;
import java.text.DecimalFormat;

class PIZZA {
    private String toppings;
    private String discount;
    private int diameter;
    private int howMany;
    private double price;

    public void setToppings(String t) {
        toppings = t;
    }

    public void setDiameter(int inches) {
        diameter = (int) (inches * 2.54);
    }

    public void setHowMany(int h) {
        howMany = h;
    }

    public void setDiscount(String d) {
        discount = d;
    }

    public String getToppings() {
        return toppings;
    }

    public int getDiameter() {
        return diameter;
    }

    public double getPrice() {
        double toppingPrice = 0;

        if (toppings.equalsIgnoreCase("Pepperoni")) {
            toppingPrice = 100;
        } else if (toppings.equalsIgnoreCase("Ham and Cheese")) {
            toppingPrice = 150;
        } else if (toppings.equalsIgnoreCase("Hawaiian")) {
            toppingPrice = 200;
        }

        price = (toppingPrice + diameter * 5) * howMany;

        if (discount.equalsIgnoreCase("Senior-PWD")) {
            price = price - price * 0.20;
        } else if (discount.equalsIgnoreCase("VIP")) {
            price = price - price * 0.12;
        }

        return price;
    }
}

class BILL_ORDER {
    public double OUT(PIZZA p) {
        double bill = p.getPrice();
        DecimalFormat df = new DecimalFormat("0.00");
        JOptionPane.showMessageDialog(null,
                "You ordered for " + p.getToppings() + " Pizza\n"
                + "with the size of " + p.getDiameter() + " centimeter\n"
                + "for the total price of PHP" + df.format(bill));
        return bill;
    }
}

public class TAKE_ORDER {
    public static void main(String[] args) {
        PIZZA PIE = new PIZZA();
        BILL_ORDER b = new BILL_ORDER();
        DecimalFormat df = new DecimalFormat("0.##");

        String[] toppingOptions = {"", "Pepperoni", "Ham and Cheese", "Hawaiian"};
        String toppings = "";

        while (true) {
            toppings = (String) JOptionPane.showInputDialog(
                    null,
                    "Choose Toppings",
                    "Pizza Program",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    toppingOptions,
                    toppingOptions[0]);

            if (toppings == null) {
                int cancel = JOptionPane.showConfirmDialog(null, "Want to CANCEL the order?",
                        "Confirm Question", JOptionPane.YES_NO_OPTION);
                if (cancel == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Thank you!");
                    System.exit(0);
                }
                continue;
            }

            if (toppings.equals("")) {
                continue;
            }

            int addPrice = 0;
            if (toppings.equals("Pepperoni")) {
                addPrice = 100;
            } else if (toppings.equals("Ham and Cheese")) {
                addPrice = 150;
            } else if (toppings.equals("Hawaiian")) {
                addPrice = 200;
            }

            JOptionPane.showMessageDialog(null, "You chose " + toppings
                    + "\nwhich cost additional " + addPrice + "php!");

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure?\nYou want " + toppings + "?",
                    "Confirm Question",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                break;
            }
        }
        PIE.setToppings(toppings);

        int inches = 0;
        while (true) {
            String diaInput = JOptionPane.showInputDialog("Enter Diameter in inches");
            if (diaInput == null) {
                int cancel = JOptionPane.showConfirmDialog(null, "Want to CANCEL the order?",
                        "Confirm Question", JOptionPane.YES_NO_OPTION);
                if (cancel == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Thank you!");
                    System.exit(0);
                }
                continue;
            }

            try {
                inches = Integer.parseInt(diaInput);
                if (inches > 0) {
                    break;
                }
            } catch (NumberFormatException e) {
            }
        }
        PIE.setDiameter(inches);

        int howMany = 0;
        while (true) {
            String qtyInput = JOptionPane.showInputDialog("Enter How Many?");
            if (qtyInput == null) {
                int cancel = JOptionPane.showConfirmDialog(null, "Want to CANCEL the order?",
                        "Confirm Question", JOptionPane.YES_NO_OPTION);
                if (cancel == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Thank you!");
                    System.exit(0);
                }
                continue;
            }

            try {
                howMany = Integer.parseInt(qtyInput);
                if (howMany > 0) {
                    break;
                }
            } catch (NumberFormatException e) {
            }
        }
        PIE.setHowMany(howMany);

        String[] discountOptions = {"N/A", "Senior-PWD", "VIP"};
        int discChoice = -1;
        while (discChoice == -1) {
            discChoice = JOptionPane.showOptionDialog(
                    null,
                    "Choose Discount:",
                    "Pizza Program",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    discountOptions,
                    discountOptions[2]);
        }

        String discount = discountOptions[discChoice];
        if (!discount.equals("N/A")) {
            while (true) {
                String cardInput = JOptionPane.showInputDialog("Enter " + discount + " Card Number:");
                if (cardInput == null) {
                    int cancel = JOptionPane.showConfirmDialog(null, "Want to CANCEL the order?",
                            "Confirm Question", JOptionPane.YES_NO_OPTION);
                    if (cancel == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "Thank you!");
                        System.exit(0);
                    }
                    continue;
                }

                try {
                    Integer.parseInt(cardInput);
                    break;
                } catch (NumberFormatException e) {
                }
            }
        }
        PIE.setDiscount(discount);

        double amountDue = b.OUT(PIE);

        double received = 0;
        while (true) {
            String recInput = JOptionPane.showInputDialog("Enter Amount:");
            if (recInput == null) {
                int cancel = JOptionPane.showConfirmDialog(null, "Want to CANCEL the order?",
                        "Confirm Question", JOptionPane.YES_NO_OPTION);
                if (cancel == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Thank you!");
                    System.exit(0);
                }
                continue;
            }

            try {
                received = Double.parseDouble(recInput);
                if (received > amountDue) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient Amount");
                }
            } catch (NumberFormatException e) {
            }
        }

        double change = received - amountDue;
        JOptionPane.showMessageDialog(null, "Your CHANGE is Php" + df.format(change));
        JOptionPane.showMessageDialog(null, "Thank you!");
    }
}