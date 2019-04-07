
package kalkulatorkonsolowy;

import java.util.LinkedList;

public class KalkulatorKonsolowy {
    
    public static boolean jestLiczba(String nazwa)
    {
        try{
        Double.parseDouble(nazwa);
        return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    
    public static boolean jestDzialaniem(String nazwa)
    {
        switch(nazwa)
        {
        case "+": return true;
        case "-": return true;
        case "*": return true;
        case "/": return true;
        case "^": return true;
        default: return false;
        }
    }
    public static int wartoscDzialania(String nazwa)
    {
        switch(nazwa)
        {
            case "+": return 1;
            case "-": return 1;
            case "*": return 3;
            case "/": return 3;
            case "^": return 5;
            default: return 0;
        }
    }
    
    public static boolean jestFunkcja(String nazwa)
    {
        switch(nazwa)
        {
            case "sin": return true; 
            case "cos": return true; 
            case "tan": return true; 
            case "cot": return true;
            case "exp": return true;
            case "abs": return true;
            case "log": return true;
            default : return false;
        }
    }
    public static String postfix(String funkcja)
    {
        String wynik = new String("");
        LinkedList<String> stos = new LinkedList<>();
        
        for(String wskazany : funkcja.split(" "))
        {  
            if(jestLiczba(wskazany)) wynik = wynik + wskazany + " ";
            if(jestFunkcja(wskazany)) stos.push(wskazany);
            if(jestDzialaniem(wskazany))
            {
                
                while(!stos.isEmpty()&&
                        ((jestDzialaniem(stos.peek())&&(wartoscDzialania(wskazany)<=wartoscDzialania(stos.peek())))
                        ||jestFunkcja(stos.peek())))
                    wynik = wynik + stos.pop() + " ";
            stos.push(wskazany);
            }
            if(wskazany.equals("(")) stos.push(wskazany);
            if(wskazany.equals(")")) 
            {
                while(!stos.peek().equals("("))
                    wynik = wynik + stos.pop() +" ";
                stos.pop();
            }
        }
        while(!stos.isEmpty()) wynik = wynik + stos.pop() + " ";
        return wynik;
    }
    public static double wynikZPostifxu(String postfix)
    {
        LinkedList<String> stos = new LinkedList<>();
        
        for(String wskazany : postfix.split(" "))
        {
            if(jestDzialaniem(wskazany))
            {
                double b = 0;
                double a = Double.parseDouble(stos.pop());
                if(!(stos.peek()==null))
                b = Double.parseDouble(stos.pop());
                switch(wskazany)
                {
                    case "+": stos.push(""+(b+a)); break;
                    case "-": stos.push(""+(b-a)); break;
                    case "*": stos.push(""+(b*a)); break;
                    case "/": stos.push(""+(b/a)); break;
                    case "^": stos.push(""+(Math.pow(b, a))); break;
                }
            }
            else if(jestFunkcja(wskazany))
            {
                double a;
                switch(wskazany)
                {
                    case "sin": a = Double.parseDouble(stos.pop()); stos.push(""+Math.sin(a)); break;
                    case "cos": a = Double.parseDouble(stos.pop()); stos.push(""+Math.cos(a)); break;
                    case "tan": a = Double.parseDouble(stos.pop()); stos.push(""+Math.tan(a)); break;
                    case "cot": a = Double.parseDouble(stos.pop()); stos.push(""+((double)1/Math.tan(a))); break;
                    case "exp": a = Double.parseDouble(stos.pop()); stos.push(""+Math.exp(a)); break;
                    case "abs": a = Double.parseDouble(stos.pop()); stos.push(""+Math.abs(a)); break;
                    case "log": a = Double.parseDouble(stos.pop()); stos.push(""+Math.log(a)); break;
                }
            }
            else if(jestLiczba(wskazany))
            stos.push(wskazany);
        }
        return Double.parseDouble(stos.pop());
    }
    public static void main(String[] args) {
        
        
        String funkcja = "exp ( -1 )";
        funkcja = postfix(funkcja);
        System.out.println(funkcja);
        System.out.println(wynikZPostifxu(funkcja));
    }
    
}
