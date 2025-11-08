package com.clinica.clinica_plus.scheduler;

import com.clinica.clinica_plus.model.Consulta;
import com.clinica.clinica_plus.model.StatusConsulta;
import com.clinica.clinica_plus.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class LembreteScheduler {

    @Autowired
    private ConsultaRepository consultaRepository;


    @Scheduled(fixedRate = 60000)
    public void verificarConsultasProximas() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime daqui24h = agora.plusHours(24);

        List<Consulta> consultas = consultaRepository.findAll();

        consultas.stream()
                .filter(c -> c.getStatus() == StatusConsulta.AGENDADA)
                .filter(c -> !Boolean.TRUE.equals(c.getLembreteEnviado()))
                .filter(c -> c.getDataHora().isAfter(agora) && c.getDataHora().isBefore(daqui24h))
                .forEach(c -> {
                    System.out.printf(
                            "📅 [LEMBRETE] Consulta de %s com Dr(a). %s às %s%n",
                            c.getPaciente().getNome(),
                            c.getMedico().getNome(),
                            c.getDataHora()
                    );

                    // Marca o lembrete como "enviado"
                    c.setLembreteEnviado(true);
                    consultaRepository.save(c);
                });
    }
}
