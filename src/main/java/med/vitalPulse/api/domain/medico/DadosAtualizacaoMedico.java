package med.vitalPulse.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.vitalPulse.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
