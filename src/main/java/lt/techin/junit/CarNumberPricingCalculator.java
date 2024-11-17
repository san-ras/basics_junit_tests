package lt.techin.junit;

class CarNumberPricingCalculator {

    /**
     * Naudojam TDD
     * Kursime programą Regitrai, kuri turi paskaičiuoti kainą pagal
     * pageidautiną automobilio numerį. Kainą politika yra tokia: Jeigu visos
     * trys raidės yra vienodos, arba jeigu visi trys skaičiai vienodi arba
     * "001","666" - numerio kaina 1000 eur.
     * Jeigu trys raidės vienodos IR trys skaičiai vienodi - numerio kaina 5000 EUR.
     * Jeigu raidžių rinkinys yra
     * vienas iš "GOD", "KLR", "BOS" numerio kaina 2000 EUR. Jeigu prie aukščiau
     * minėto raidžių rinkinio pridėsime tris vienodus skaičius - 7000 EUR.
     * Jeigu numeris yra ne iš trijų simbolių ir trijų skaičių (1-6 simboliai)
     * jis yra vardinis - kaina 10 000 EUR.
     * Jei skaičiuoklei paduodamas blogo formato numeris - turi mesti
     * IllegalArgumentException su pranešimu - "Incorrect plate number format. Must be 1-6 symbols
     * latin letters and number combination"
     * mažosiomis ir d P.S. NIEKADA realiose sistemose nenaudokite float/double
     * pinigų ir kitoms tikslioms operacijoms tam naudokite BigDecimal tipą!
     */


    // TESTS ARE IN TEST FOLDER

    public double calculatePrice(String number) {

        number = number.toUpperCase();

        if (numberIsValid(number)) {

            if (numberIsPersonalised(number)) {
                return 10000;
            } else if (allDigitsSame(number) && allLettersSame(number)) {
                return 5000;
            } else if (numberIsBOS(number) || numberIsKLR(number) || numberIsGOD(number)) {
                if (allDigitsSame(number)) {
                    return 7000;
                } else {
                    return 2000;
                }
            } else if (allDigitsSame(number) || allLettersSame(number) || numberIs001(number)) {
                return 1000;
            }
        } else {
            throw new IllegalArgumentException("Incorrect plate number format. Must be 1-6 symbols: latin letters and number combination");
        }
        return 0;
    }

    public boolean allLettersSame(String number) {
        return number.charAt(0) == number.charAt(1) && number.charAt(0) == number.charAt(2);
    }

    public boolean allDigitsSame(String number) {
        return number.charAt(3) == number.charAt(4) && number.charAt(3) == number.charAt(5);
    }

    public boolean numberIsPersonalised(String number) {
        return !number.matches("^[a-zA-Z]{3}[0-9]{3}$");
    }

    public boolean numberIsValid(String number) {
        return number != null && number.matches("^[a-zA-Z0-9]{1,6}$");
    }

    public boolean numberIs001(String number) {
        return number.charAt(3) == '0' && number.charAt(4) == '0' && number.charAt(5) == '1';
    }

    public boolean numberIsGOD(String number) {
        return number.charAt(0) == 'G' && number.charAt(1) == 'O' && number.charAt(2) == 'D';
    }

    public boolean numberIsKLR(String number) {
        return number.charAt(0) == 'K' && number.charAt(1) == 'L' && number.charAt(2) == 'R';
    }

    public boolean numberIsBOS(String number) {
        return number.charAt(0) == 'B' && number.charAt(1) == 'O' && number.charAt(2) == 'S';
    }

}