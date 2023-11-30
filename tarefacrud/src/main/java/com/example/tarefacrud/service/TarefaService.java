package com.example.tarefacrud.service;

import com.example.tarefacrud.entitie.Tarefa;
import com.example.tarefacrud.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;

    @Autowired
    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<Tarefa> getAllTarefas() {
        return tarefaRepository.findAll();
    }

    public Tarefa createTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa getTarefaById(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa not found with id: " + id));
    }

    public Tarefa updateTarefa(Long id, Tarefa tarefaDetails) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
        if (tarefaOptional.isPresent()) {
            Tarefa tarefa = tarefaOptional.get();

            tarefa.setTitulo(tarefaDetails.getTitulo());
            tarefa.setDescricao(tarefaDetails.getDescricao());
            tarefa.setFinalizada(tarefaDetails.isFinalizada());

            return tarefaRepository.save(tarefa);
        } else {
            throw new RuntimeException("Tarefa not found with id: " + id);
        }
    }

    public boolean deleteTarefa(Long id) {
        Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
        if (tarefaOptional.isPresent()) {
            tarefaRepository.delete(tarefaOptional.get());
            return true; // Indica que a tarefa foi deletada com sucesso
        } else {
            return false; // Indica que a tarefa com o ID fornecido não foi encontrada
        }
    }
}
