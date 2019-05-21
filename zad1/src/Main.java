public class Main implements IArithmeticsDiff{
    public static void main(String[] args){
        System.out.println("Grupa: ZPI_2019_Dzienni_IO1_Grupa3, Rola:Developer, Team Leader: Lucat0n");
        System.out.println("Grupa: ZPI_2019_Dzienni_IO1_Grupa3, Rola:Operations, Team Leader: 209450");
        System.out.println("Grupa: ZPI_2019_Dzienni_IO1_Grupa3, Rola:Developer, GithubID: pawel10x");    
        System.out.println("Grupa: ZPI_2019_Dzienni_IO1_Grupa3, Rola:Testy, Github Id: veraxys");
    }

    @Override
    public double Difference(double A, double B) {
        return A-B;
    }
}

interface IArithmeticsDiff
{
    double Difference(double A, double B);
}