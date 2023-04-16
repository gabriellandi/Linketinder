package linketinder.DAO

import linketinder.DAO.bancos.IConnect
import linketinder.DAO.bancos.vagas.VagasDAO

class VagasAccess extends Access{
    @Override
    protected IConnect createAccess() {
        return new VagasDAO()
    }
}
