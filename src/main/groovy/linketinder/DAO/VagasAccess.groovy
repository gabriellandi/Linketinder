package linketinder.DAO

import linketinder.DAO.bancos.IConnect
import linketinder.DAO.bancos.VagasDAO

class VagasAccess extends Access{
    @Override
    protected IConnect createAccess() {
        return new VagasDAO()
    }
}
