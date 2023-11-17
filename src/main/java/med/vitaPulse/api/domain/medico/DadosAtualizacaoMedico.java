package med.vitaPulse.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.vitaPulse.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
