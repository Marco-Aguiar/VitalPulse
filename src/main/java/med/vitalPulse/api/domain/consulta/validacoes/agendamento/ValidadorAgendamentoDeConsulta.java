package med.vitalPulse.api.domain.consulta.validacoes.agendamento;

import med.vitalPulse.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);

}
