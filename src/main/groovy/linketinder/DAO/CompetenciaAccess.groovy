package linketinder.DAO

import linketinder.DAO.bancos.IConnect
import linketinder.DAO.bancos.CompetenciasDAO

class CompetenciaAccess extends Access{
    @Override
    protected IConnect createAccess() {
        return new CompetenciasDAO()
    }
}
