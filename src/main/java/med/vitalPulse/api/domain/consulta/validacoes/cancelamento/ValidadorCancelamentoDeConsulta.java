package med.vitalPulse.api.domain.consulta.validacoes.cancelamento;

import med.vitalPulse.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);

}
