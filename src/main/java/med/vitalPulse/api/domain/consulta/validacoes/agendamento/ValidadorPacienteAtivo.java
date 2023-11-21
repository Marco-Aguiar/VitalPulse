package med.vitalPulse.api.domain.consulta.validacoes.agendamento;

import med.vitalPulse.api.domain.consulta.DadosAgendamentoConsulta;
import med.vitalPulse.api.domain.paciente.PacienteRepository;
import med.vitalPulse.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
