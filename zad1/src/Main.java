public class Main implements IArithmeticsMult,IArithmeticsAdd,IArithmeticsDiff{
}
    public static void main(String[] args){

        /**
         * Druk danych teamu
         */
        System.out.println("Grupa: ZPI_2019_Dzienni_IO1_Grupa3, Rola:Developer, Team Leader: Lucat0n");
        System.out.println("Grupa: ZPI_2019_Dzienni_IO1_Grupa3, Rola:Operations, Team Leader: 209450");
        System.out.println("Grupa: ZPI_2019_Dzienni_IO1_Grupa3, Rola:Developer, GithubID: pawel10x");
        System.out.println("Grupa: ZPI_2019_Dzienni_IO1_Grupa3, Rola:Testy, Github Id: veraxys");

        System.out.println("Developer Lucat0n zaimplementował interfejs Addition");
    }

    @Override
    public double Addition(double A, double B) {
        return A + B;
    }
}

/**
 * Interfejs do dodawania liczb
 */
interface IArithmeticsAdd {
    double Addition(double A, double B);
    //VERAXYS
    public double Multiplication(double A, double B) {
        return A*B;
    }

    @Override
    public double Difference(double A, double B) {
        return A-B;
    }

}

interface IArithmeticsDiv{
    double Division(double A, double B);
}


interface IArithmeticsDiff
{
    double Difference(double A, double B);

interface IArithmeticsMult
{
    double Multiplication(double A, double B);
}

