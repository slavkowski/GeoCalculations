package pl.sats;


import pl.sats.FieldObservationsObjects.Geoid;


public class Main {
    public static void main(String[] args) {
        Geoid g;
        Geoid geoid = new Geoid();
        geoid.setTest("Test1");
//        try {
//            FileOutputStream fous = new FileOutputStream(new File("geoid.gda"));
//            ObjectOutputStream oous = new ObjectOutputStream(fous);
//            oous.writeObject(geoid);
//            oous.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            FileInputStream fis = new FileInputStream("geoid.gda"); //utworzenie strumienia wejściowego
//            ObjectInputStream ois = new ObjectInputStream(fis); //utworzenie obiektu odczytującego obiekty ze strumienia
//
//            g = (Geoid) ois.readObject(); //deserializacja obiektu
//            System.out.println(g.toString());
//
//        } catch (ClassNotFoundException | IOException e) {
//            e.printStackTrace();
//        }
//
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        try {
//            ObjectOutputStream oous2 = new ObjectOutputStream(bos);
//            oous2.writeObject(geoid);
//            oous2.flush();
//            oous2.close();
//            byte [] data = bos.toByteArray();
//            String s = new String(data);
//            System.out.println(s);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
