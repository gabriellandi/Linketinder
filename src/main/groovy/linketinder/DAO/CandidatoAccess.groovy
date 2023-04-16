package linketinder.DAO

import linketinder.DAO.bancos.IConnect
import linketinder.DAO.bancos.candidato.CandidatoDAO

class CandidatoAccess extends Access{
    @Override
    protected IConnect createAccess() {
        return new CandidatoDAO()
    }
}
