package br.ufjf.dcc.model.repository;

import br.ufjf.dcc.model.Conta;
import br.ufjf.dcc.model.Usuario;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;

public class ContaRepository implements Repository<Conta>{

    private static final String PATH = DIRECTORY+ File.separator +"contas.json";

    @Override
    public void save(List<Conta> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);
        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();
        Arquivo.salva(PATH, json);
    }

    public void save(Conta item){
        List<Conta> itens = findAll();

        boolean isNew = true;
        for(Conta c : itens){
            if (c.getId().equals(item.getId())) {
                isNew = false;
                break;
            }
        }
        if(isNew) {itens.add(item);}
        else {
            Conta contaOld = findById(item.getId());
            itens.remove(contaOld);
            itens.add(item);
        }
        save(itens);
    }

    public Conta findByCliente(Usuario item){
        List<Conta> contas = findAll();
        Conta contaEncontrada = null;
        for(Conta c : contas){
            if(c.getCliente().equals(item)){
                contaEncontrada = c;
            }
        }
        return contaEncontrada;
    }

    public Conta findById(UUID id){
        List<Conta> contas = findAll();
        for(Conta c : contas){
            if(c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

    @Override
    public List<Conta> findAll() {
        Gson gson = new Gson();
        String json = Arquivo.le(PATH);

        List<Conta> contas = new ArrayList<>();
        if(!json.trim().equals("")) {
            Type tipoLista = new TypeToken<List<Conta>>() {
            }.getType();
            contas = gson.fromJson(json, tipoLista);

            if (contas == null)
                contas = new ArrayList<>();
        }
        return contas;
    }

}
