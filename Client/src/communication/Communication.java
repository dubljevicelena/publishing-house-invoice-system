/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import domain.Faktura;
import domain.FakturaPretraga;
import domain.Grad;
import domain.Knjiga;
import domain.Knjizara;
import domain.KnjizaraPretraga;
import domain.Racunovodja;
import domain.Smena;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Dell
 */
public class Communication {
    
    private static Communication instance;
    private Socket socket;
    private Receiver receiver;
    private Sender sender;
    
    private Communication(){
        
    }
    
    public static Communication getInstance(){
        if (instance == null) 
            instance = new Communication();
        return instance;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    public void connect() {
        try {
            socket = new Socket("localhost", 9000);
            sender = new Sender(socket);
            receiver = new Receiver(socket);
        } catch (IOException ex) {
            System.out.println("Neuspesno povezivanje!");
        }
    }
    
    public Racunovodja prijaviRacunovodja(String korisnickoIme, String lozinka) {
        try {
            Racunovodja r = new Racunovodja();
            r.setKorisnickoIme(korisnickoIme);
            r.setLozinka(lozinka);
            Request zahtev = new Request(Operation.PRIJAVI_RACUNOVODJU, r);
            sender.send(zahtev);
            Response o = (Response) receiver.receive();
            r = (Racunovodja) o.getResult();
            return r;
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean ubaciRacunovodja(Racunovodja r) throws Exception {
        Request zahtev = new Request(Operation.UBACI_RACUNOVODJU, r);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getResult() instanceof Boolean)
            return (boolean) o.getResult();
        else
            throw new Exception("Greska ubaciRacunovodja!");
    }
    
    public List<Racunovodja> vratiListuSviRacunovodja() throws Exception {
        Request zahtev = new Request(Operation.VRATI_LISTU_SVI_RACUNOVODJA, null);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getResult() instanceof List)
            return (List<Racunovodja>) o.getResult();
        else
            throw new Exception("Greska vratiListuSviRacunovodja!");
    }
    
    public List<Knjizara> vratiListuSviKnjizara() throws Exception {
        Request zahtev = new Request(Operation.VRATI_LISTU_SVI_KNJIZARA, null);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getResult() instanceof List)
            return (List<Knjizara>) o.getResult();
        else
            throw new Exception("Greska vratiListuSviKnjizara!");
        
    }
    
    public List<Knjiga> vratiListuSviKnjiga() throws Exception {
        Request zahtev = new Request(Operation.VRATI_LISTU_SVI_KNJIGA, null);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getResult() instanceof List)
            return (List<Knjiga>) o.getResult();
        else
            throw new Exception("Greska vratiListuSviKnjiga!");
    }
    
    public List<Grad> vratiListuSviGrad() throws Exception {
        Request zahtev = new Request(Operation.VRATI_LISTU_SVI_GRAD, null);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getResult() instanceof List)
            return (List<Grad>) o.getResult();
        else
            throw new Exception("Greska vratiListuSviGrad!");
    }
    
    public boolean kreirajFaktura(Faktura f) throws Exception {
        Request zahtev = new Request(Operation.ZAPAMTI_FAKTURU, f);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getResult() instanceof Boolean)
            return (boolean) o.getResult();
        else
            throw new Exception("Greska kreirajFakturu!");
    }
    
    public boolean promeniFaktura(Faktura f) throws Exception {
        Request zahtev = new Request(Operation.PROMENI_FAKTURU, f);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getResult() instanceof Boolean)
            return (boolean) o.getResult();
        else
            throw new Exception("Greska promeniFakturu!");
    }
    
    public Faktura pretraziFaktura(int idF) throws Exception {
        Request zahtev = new Request(Operation.PRETRAZI_FAKTURU, idF);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getResult() instanceof Faktura)
            return (Faktura) o.getResult();
        else
            throw new Exception("Greska pretraziFakturu!");
    }
    
    public List<Faktura> vratiListuFaktura(FakturaPretraga pretraga) throws Exception {
        Request zahtev = new Request(Operation.VRATI_LISTU_FAKTURA, pretraga);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getResult() instanceof List)
            return (List<Faktura>) o.getResult();
        else
            throw new Exception("Greska vratiListuFaktura!");
    }
    
    public boolean ubaciKnjizara(Knjizara k) throws Exception {
        Request zahtev = new Request(Operation.UBACI_KNJIZARU, k);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getException() != null) {
            throw o.getException();
        }
        if (o.getResult() instanceof Boolean) {
            return (boolean) o.getResult();
        } else {
            throw new Exception("Greska ubaciKnjizara!");
        }
    }
    
    public boolean promeniKnjizara(Knjizara k) throws Exception {
        Request zahtev = new Request(Operation.PROMENI_KNJIZARU, k);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getResult() instanceof Boolean)
            return (boolean) o.getResult();
        else
            throw new Exception("Greska promeniKnjizara!");
    }
    
    public Knjizara pretraziKnjizara(Long idK) throws Exception {
        Request zahtev = new Request(Operation.PRETRAZI_KNJIZARU, idK);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getResult() instanceof Knjizara)
            return (Knjizara) o.getResult();
        else
            throw new Exception("Greska pretraziKnjizara!");
    }
    
    public boolean obrisiKnjizara(Knjizara k) throws Exception {
        Request zahtev = new Request(Operation.OBRISI_KNJIZARU, k);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getException() != null) {
            throw o.getException();
        }
        if (o.getResult() instanceof Boolean)
            return (boolean) o.getResult();

        throw new Exception("Greska obrisiKnjizara!");
    }
    
    public List<Knjizara> vratiListuKnjizara(KnjizaraPretraga pretraga) throws Exception {
        Request zahtev = new Request(Operation.VRATI_LISTU_KNJIZARA, pretraga);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getResult() instanceof List)
            return (List<Knjizara>) o.getResult();
        else
            throw new Exception("Greska vratiListuKnjizara!");
    }
    
    public boolean ubaciSmena(Smena s) throws Exception {
        Request zahtev = new Request(Operation.UBACI_SMENU, s);
        sender.send(zahtev);
        Response o = (Response) receiver.receive();
        if (o.getResult() instanceof Boolean)
            return (boolean) o.getResult();
        else
            throw new Exception("Greska ubaciSmena!");
    }

    
}
