package br.ufjf.dcc.model.repository;

import br.ufjf.dcc.model.Usuario;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class UsuarioRepository implements Repository<Usuario>{

    private static final String PATH = DIRECTORY+ File.separator +"usuarios.json";

    @Override
    public void save(List<Usuario> itens) {
        Gson gson = new Gson();
        String json = gson.toJson(itens);
        File diretorio = new File(DIRECTORY);
        if(!diretorio.exists())
            diretorio.mkdirs();
        Arquivo.salva(PATH, json);
    }

    public void save(Usuario item){
        List<Usuario> itens = findAll();
        itens.add(item);
        save(itens);
    }

    @Override
    public List<Usuario> findAll() {
        Gson gson = new Gson();
        String json = Arquivo.le(PATH);

        List<Usuario> usuarios = new ArrayList<>();
        if(!json.trim().equals("")) {
            Type tipoLista = new TypeToken<List<Usuario>>() {
            }.getType();
            usuarios = gson.fromJson(json, tipoLista);

            if (usuarios == null)
                usuarios = new ArrayList<>();
        }
        return usuarios;
    }

}
