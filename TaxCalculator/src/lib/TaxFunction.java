package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	 public static int calculateTax(Employee employee) {
        int monthlyTaxableIncome = calculateMonthlyTaxableIncome(employee);
        int annualDeductible = employee.getAnnualDeductible();
        int numberOfMonthWorking = employee.getMonthWorkingInYear();

        int tax = (int) Math.round(0.05 * (monthlyTaxableIncome * numberOfMonthWorking - annualDeductible));
        return Math.max(tax, 0);
    }

    private static int calculateMonthlyTaxableIncome(Employee employee) {
        int monthlySalary = employee.getMonthlySalary();
        int otherMonthlyIncome = employee.getOtherMonthlyIncome();
        int numberOfChildren = employee.getNumberOfChildren();

        int nonTaxableIncome = calculateNonTaxableIncome(employee);
        return monthlySalary + otherMonthlyIncome - nonTaxableIncome;
    }

    private static int calculateNonTaxableIncome(Employee employee) {
        int nonTaxableIncome = 54000000;
        if (employee.isMarried()) {
            nonTaxableIncome += 4500000;
        }
        nonTaxableIncome += Math.min(employee.getNumberOfChildren(), 3) * 1500000;
        return nonTaxableIncome;
    }
	
}
