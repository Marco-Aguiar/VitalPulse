package med.vitalpulse.api.domain.consulta.validacoes.cancelamento;

import med.vitalpulse.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);

}
