package linketinder.DAO

import linketinder.DAO.bancos.IConnect
import linketinder.DAO.bancos.PaisDAO

class PaisAccess extends Access{
    @Override
    protected IConnect createAccess() {
        return new PaisDAO()
    }
}
