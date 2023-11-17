package med.vitaPulse.api.medico;

import jakarta.validation.constraints.NotNull;
import med.vitaPulse.api.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
