package org.example.frigoservice.service;

import org.example.frigoservice.dto.FrigoDtoReceive;
import org.example.frigoservice.dto.FrigoDtoSend;
import org.example.frigoservice.entity.Frigo;
import org.example.frigoservice.repository.FrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrigoService {

    @Autowired
    private FrigoRepository frigoRepository;

    public FrigoService(FrigoRepository frigoRepository) {
        this.frigoRepository = frigoRepository;
    }

    private FrigoDtoSend frigoMapperFrigoDTOSend(Frigo frigo) {
        return FrigoDtoSend.builder()
                .id_frigo(frigo.getId_frigo())
                .id_utilisateur(frigo.getId_utilisateur())
                .build();
    }

    private List<FrigoDtoSend> listeFrigoMapperListeFrigoDTOSend(List<Frigo> frigos) {
        return frigos.stream().map(this::frigoMapperFrigoDTOSend).toList();
    }

    private Frigo findById(int id) {
        return frigoRepository.findById(id).orElse(null);
    }


    public List<FrigoDtoSend> getAll() {
        return listeFrigoMapperListeFrigoDTOSend((List<Frigo>) frigoRepository.findAll());
    }

    public FrigoDtoSend getById(int id) {
        return frigoMapperFrigoDTOSend(frigoRepository.findById(id).orElseThrow());
    }

    public FrigoDtoSend save(FrigoDtoReceive frigoDtoReceive) {

        Frigo frigo = Frigo.builder()
                .id_utilisateur(frigoDtoReceive.getId_utilisateur())
                .build();

        return frigoMapperFrigoDTOSend(frigoRepository.save(frigo));
    }

    public FrigoDtoSend update(int id, FrigoDtoReceive frigoDtoReceive) {
        Frigo frigo = findById(id);

        frigo.setId_utilisateur(frigoDtoReceive.getId_utilisateur());

        return frigoMapperFrigoDTOSend(frigoRepository.save(frigo));
    }

    public void delete(int id) {
        frigoRepository.delete(findById(id));
    }
}