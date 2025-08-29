package br.ufjf.dcc.model.repository;

import br.ufjf.dcc.model.Gerente;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class GerenteRepository implements Repository<Gerente>{

    private static final String PATH = DIRECTORY+ File.separator +"gerentes.json";

    @Override
    public void save(List<Gerente> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);
        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();
        Arquivo.salva(PATH, json);
    }

    public void save(Gerente item){
        List<Gerente> itens = findAll();
        itens.add(item);
        save(itens);
    }

    @Override
    public List<Gerente> findAll() {
        Gson gson = new Gson();
        String json = Arquivo.le(PATH);

        List<Gerente> gerentes = new ArrayList<>();
        if(!json.trim().equals("")) {
            Type tipoLista = new TypeToken<List<Gerente>>() {
            }.getType();
            gerentes = gson.fromJson(json, tipoLista);

            if (gerentes == null)
                gerentes = new ArrayList<>();
        }
        return gerentes;
    }

}
