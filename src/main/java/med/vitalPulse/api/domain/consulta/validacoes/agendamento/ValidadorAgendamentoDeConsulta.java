package med.vitalpulse.api.domain.consulta.validacoes.agendamento;

import med.vitalpulse.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);

}
