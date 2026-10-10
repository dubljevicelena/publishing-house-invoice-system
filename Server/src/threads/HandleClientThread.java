/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import communication.Receiver;
import communication.Sender;
import communication.Request;
import communication.Response;
import controller.Controller;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import so.knjizara.PretraziKnjizaraSO;

/**
 *
 * @author Dell
 */
public class HandleClientThread extends Thread {
    Socket socket;
    Sender sender;
    Receiver receiver;
    boolean kraj = false;

    public HandleClientThread(Socket socket) {
        this.socket = socket;
        receiver = new Receiver(socket);
        sender = new Sender(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
        try {
            Request request = (Request) receiver.receive();
            if (request == null) {
                System.out.println("Klijent prekinut. Zahtev null!");
                prekini();
                return;
            }

            Response odgovor = new Response();
            try {
                switch (request.getOperation()) {
                    case PRIJAVI_RACUNOVODJU:
                        Racunovodja r = (Racunovodja) request.getArgument();
                        r = Controller.getInstance().prijaviRacunovodju(r);
                        odgovor.setResult(r);
                        break;
                    case VRATI_LISTU_SVI_RACUNOVODJA:
                        List<Racunovodja> racunovodje = Controller.getInstance().vratiListuSviRacunovodja();
                        odgovor.setResult(racunovodje);
                        break;
                    case UBACI_RACUNOVODJU:
                        Racunovodja rac = (Racunovodja) request.getArgument();
                        Controller.getInstance().ubaciRacunovodju(rac);
                        odgovor.setResult(true);
                        break;
                    case VRATI_LISTU_SVI_GRAD:
                        List<Grad> gradovi = Controller.getInstance().vratiListuSviGrad();
                        odgovor.setResult(gradovi);
                        break;
                    case VRATI_LISTU_SVI_KNJIGA:
                        List<Knjiga> knjige = Controller.getInstance().vratiListuSviKnjiga();
                        odgovor.setResult(knjige);
                        break;
                    case VRATI_LISTU_SVI_KNJIZARA:
                        List<Knjizara> knjizare = Controller.getInstance().vratiListuSviKnjizara();
                        odgovor.setResult(knjizare);
                        break;
                    case UBACI_KNJIZARU:
                        Knjizara kn = (Knjizara) request.getArgument();
                        Controller.getInstance().ubaciKnjizaru(kn);
                        odgovor.setResult(true);
                        break;
                    case OBRISI_KNJIZARU:
                        Knjizara kObrisi = (Knjizara) request.getArgument();
                        Controller.getInstance().obrisiKnjizaru(kObrisi);
                        odgovor.setResult(true);
                        break;
                    case PROMENI_KNJIZARU:
                        Knjizara kPromeni = (Knjizara) request.getArgument();
                        Controller.getInstance().promeniKnjizaru(kPromeni);
                        odgovor.setResult(true);
                        break;
                    case PRETRAZI_KNJIZARU:
                        KnjizaraPretraga kriterijum = (KnjizaraPretraga) request.getArgument();
                        PretraziKnjizaraSO so = new PretraziKnjizaraSO();
                        so.execute(kriterijum);
                        List<Knjizara> listaRezultata = so.getListaKnjizara();
                        odgovor.setResult(listaRezultata);
                        break;
                    case VRATI_LISTU_KNJIZARA:
                        KnjizaraPretraga kriterijumKn = (KnjizaraPretraga) request.getArgument();
                        List<Knjizara> listaKn = Controller.getInstance().vratiListuKnjizara(kriterijumKn);
                        odgovor.setResult(listaKn);
                        break;
                    case ZAPAMTI_FAKTURU:
                        Faktura fak = (Faktura) request.getArgument();
                        Controller.getInstance().zapamtiFakturu(fak);
                        odgovor.setResult(true);
                        break;
                    case PROMENI_FAKTURU:
                        Faktura fakPromeni = (Faktura) request.getArgument();
                        Controller.getInstance().promeniFakturu(fakPromeni);
                        odgovor.setResult(true);
                        break;
                    case PRETRAZI_FAKTURU:
                        int idFak = (int) request.getArgument();
                        Faktura fakturaSaStavkama = Controller.getInstance().pretraziFakturu(idFak);
                        odgovor.setResult(fakturaSaStavkama);
                        break;
                    case VRATI_LISTU_FAKTURA:
                        FakturaPretraga kriterijumFak = (FakturaPretraga) request.getArgument();
                        List<Faktura> listaFak = Controller.getInstance().vratiListuFaktura(kriterijumFak);
                        odgovor.setResult(listaFak);
                        break;
                    case UBACI_SMENU:
                        Smena sm = (Smena) request.getArgument();
                        Controller.getInstance().ubaciSmenu(sm);
                        odgovor.setResult(true);
                        break;

                    default:
                        System.out.println("Nepoznata operacija: " + request.getOperation());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                odgovor.setException(ex);
            }
            sender.send(odgovor);
            } catch (Exception e) {
                System.out.println("Greška u komunikaciji sa klijentom: " + e.getMessage());
                prekini();
            }
        }
    
    }

    public void prekini() {
        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(HandleClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
    
    
}
