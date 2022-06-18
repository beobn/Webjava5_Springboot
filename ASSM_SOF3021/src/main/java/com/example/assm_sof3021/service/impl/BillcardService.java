package com.example.assm_sof3021.service.impl;

import com.example.assm_sof3021.modal.BillCard;
import com.example.assm_sof3021.modal.Card;
import com.example.assm_sof3021.modal.Cart;
import com.example.assm_sof3021.modal.Enum.Status;
import com.example.assm_sof3021.repository.IBillcard;
import com.example.assm_sof3021.service.IBillcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillcardService implements IBillcardService {

    @Autowired
    IBillcard repositori;

    @Override
    public BillCard add(BillCard billCard) {
        return repositori.save(billCard) ;
    }

    @Override
    public List<BillCard> finbyidorder(Integer id) {
        return repositori.findByCarts_IdOrder(id);
    }



    @Override
    public Page<BillCard> getByPage(int pageNumber, int maxRecord,  String tim) {
        Pageable pageable = PageRequest.of(pageNumber, maxRecord);
        if(tim.equals("")){
            Page<BillCard> pagee = repositori.findAll( pageable);
            return pagee;
        }else{
            Page<BillCard> pagee = repositori.findByCards_Seri(tim,pageable);

            if(pagee.getTotalElements()==0){
                pagee=repositori.findByCarts_IdOrder(Integer.parseInt(tim),pageable);
                return pagee;
            }
            return pagee;
        }
    }
    public String stringPrice(int so){
        int du=0,vitri=1;
        String string="";
        String hi= String.valueOf(so);



        while (so!=0){

            du=so%10;
            string=stringSo(du)+" "+ vitri(vitri)+ " "+string;
            so=so/10;
            vitri=vitri+1;

        }
        string= string+"đồng";

//            if(hi.lastIndexOf("11")==hi.length()-5){
//                System.out.println(hi.lastIndexOf("11"));
//                System.out.println(string.replace("một mươi một","mười một").replace("mươi","").replace("không trăm không mươi không","").replace("không","").replace("  "," ").replace("  "," ").replace("  "," "));
//                return string;
//            }
//        if(hi.lastIndexOf("10")==hi.length()-5){
//            System.out.println(10);
//            System.out.println(string.replace("một mươi","mười").replace("  "," ").replace("  "," ").replace("  "," "));
//
//            return string;
//
//        }
//
//
//        if(hi.lastIndexOf("000")==hi.length()-3){
//            System.out.println(hi.lastIndexOf("000"));
//            System.out.println(string.replace("không trăm không mươi không","").replace("không","").replace("  "," ").replace("  "," "));
//
//            return string;
//
//        }
//        if(hi.lastIndexOf("00")==hi.length()-2){
//            System.out.println(hi.lastIndexOf("00"));
//            System.out.println(string.replace("không mươi không","").replace("không","").replace("  "," ").replace("  "," "));
//            return string;
//        }
        System.out.println(string.replace("không nghìn không trăm không mươi không","nghìn").replace("nghìn không trăm không mươi không","nghìn").replace("trăm không mươi không","trăm").replace("một mươi","mười").replace("mươi một","mươi mốt")
                .replace("không mươi","linh").replace("mười không triệu","mười triệu").replace("không trăng linh không triệu không trăm linh nghìn","")
                .replace("không trăm linh nghìn","").replace("mươi không triệu","mươi triệu").replace("trăm linh nghìn","trăm nghìn")
        );


        return string.replace("không nghìn không trăm không mươi không","nghìn").replace("nghìn không trăm không mươi không","nghìn").replace("trăm không mươi không","trăm").replace("một mươi","mười").replace("mươi một","mươi mốt")
                .replace("không mươi","linh").replace("mười không triệu","mười triệu").replace("không trăng linh không triệu không trăm linh nghìn","")
                .replace("không trăm linh nghìn","").replace("mươi không triệu","mươi triệu").replace("trăm linh nghìn","trăm nghìn").toUpperCase();
    }
    public String stringSo(int so){
        switch (so){
            case 0:return "không";
            case 1:return "một";
            case 2:return "hai";
            case 3:return "ba";
            case 4:return "bốn";
            case 5:return "năm";
            case 6:return "sáu";
            case 7:return "bảy";
            case 8:return "tám";
            case 9:return "chín";
        }
        return "";
    }
    public String vitri(int vt){
        switch (vt){
            case 2:return "mươi";
            case 3:return "trăm";
            case 4:return "nghìn";
            case 5:return "mươi";
            case 6:return "trăm";
            case 7:return "triệu";
            case 8:return "mươi";
            case 9:return "trăm";
            case 10:return "tỷ";
        }
        return "";
    }
}
