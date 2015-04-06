package pl.polsl.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet providing information about full W Machine
 * architecture. Defines enum containing all available
 * registers and signals.
 * @author Tomasz Rzepka
 * @version 1.0
 */
@WebServlet("/ArchitectureInfo")
public class ArchitectureInfo extends WMachineServletBase {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	
	/** Gson instance used to serialize architecture info. */
	private final Gson GSON;

    /**
     * Default constructor. Initializes registers W Machine serializer
     * into Gson object.
     */
    public ArchitectureInfo() {
    	final GsonBuilder gsonBuilder = new GsonBuilder();
        GSON = gsonBuilder.create();
    }
    
	/**
	 * @see WMachineServletBase#processRequest(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Writer writer = response.getWriter();
		writer.write("{ \"Registers\": ");
		Map<AvailableRegisters, Integer> registers = new HashMap<>();
		for(AvailableRegisters value : AvailableRegisters.values())
			registers.put(value, value.ID);
		GSON.toJson(registers, writer);
		writer.write(", \"Signals\": ");
		Map<AvailableSignals, Integer> signals = new HashMap<>();
		for(AvailableSignals value : AvailableSignals.values())
			signals.put(value, value.ID);
		GSON.toJson(signals, writer);
		writer.write("}");
	}
	
	/**
	 * List of registers available in full architecture.
	 * @author Tomasz Rzepka
	 * @version 1.0
	 */
	public static enum AvailableRegisters {
		/** Program counter register. */
		PROGRAM_COUNTER(1),
		/** Instruction register. */
		INSTRUCTION(2),
		/** Accumulator. */
		ACCUMULATOR(3),
		/** Memory address register. */
		MEMORY_ADDRESS(4),
		/** Memory data register. */
		MEMORY_DATA(5),
		/** Stack pointer register. */
		STACK_POINTER(6),
		/** Flags register. */
		FLAGS(7),
		/** Data register X. */
		DATA_X(8),
		/** Data register Y. */
		DATA_Y(9),
		/** I/O port register. */
		IO_PORT(10),
		/** Strobe signal register. */
		STROBE(11);
		
		/** Register ID. */
		public final Integer ID;
		
		/**
		 * Constructs enum value.
		 * @param id - ID of a register.
		 */
		private AvailableRegisters(Integer id) {
			this.ID = id;
		}
	}
	
	/**
	 * List of signals available in full architecture.
	 * @author Tomasz Rzepka
	 * @version 1.0
	 */
	public static enum AvailableSignals {
		/** Pass value from address bus to program counter. */
		PROGRAM_COUNTER_IN(1),
		/** Pass value from program counter to address bus. */
		PROGRAM_COUNTER_OUT(2),
		/** Increment value stored in program counter. */
		PROGRAM_COUNTER_INCREMENT(3),
		/** Pass value from program counter to data bus. */
		PROGRAM_COUNTER_OUT_TO_DATA_BUS(4),
		/** Pass value from data bus to instruction register. */
		INSTRUCTION_IN(5),
		/** Pass value from instruction register to address bus. */
		INSTRUCTION_OUT(6),
		/** Pass value from arithmetic logic unit to accumulator. */
		ACCUMULATOR_IN(7),
		/** Pass value from accumulator to data bus. */
		ACCUMULATOR_OUT(8),
		/** Increment value stored in accumulator. */
		ACCUMULATOR_INCREMENT(9),
		/** Decrement value stored in accumulator. */
		ACCUMULATOR_DECREMENT(10),
		/** Pass value from address bus to memory address register. */
		MEMORY_ADDRESS_IN(11),
		/** Pass value from data bus to memory data register. */
		MEMORY_DATA_IN(12),
		/** Pass value from memory data register to data bus. */
		MEMORY_DATA_OUT(13),
		/** Pass value from memory data register to memory cell selected by memory address register. */
		MEMORY_WRITE(14),
		/** Pass value from memory cell selected by memory address register to memory data register. */
		MEMORY_READ(15),
		/** Pass value from address bus to stack pointer register. */
		STACK_POINTER_IN(16),
		/** Pass value from stack pointer register to address bus. */
		STACK_POINTER_OUT(17),
		/** Increment value stored in stack pointer register. */
		STACK_POINTER_INCREMENT(18),
		/** Decrement value stored in stack pointer register. */
		STACK_POINTER_DECREMENT(19),
		/** Pass value from one bus to another bus. */
		BUS_CONNECTION(20),
		/** Perform addition in arithmetic logic unit. */
		ALU_ADD(21),
		/** Perform subtraction in arithmetic logic unit. */
		ALU_SUBTRACT(22),
		/** Perform multiplication in arithmetic logic unit. */
		ALU_MULTIPLY(23),
		/** Perform division in arithmetic logic unit. */
		ALU_DIVIDE(24),
		/** Perform copy in arithmetic logic unit. */
		ALU_COPY(25),
		/** Perform logical shift right in arithmetic logic unit. */
		ALU_SHIFT_RIGHT(26),
		/** Perform logical conjunction in arithmetic logic unit. */
		ALU_CONJUNCTION(27),
		/** Perform logical alternative in arithmetic logic unit. */
		ALU_ALTERNATIVE(28),
		/** Perform logical negation in arithmetic logic unit. */
		ALU_NEGATION(29),
		/** Pass value from data bus to data register X. */
		DATA_X_IN(30),
		/** Pass value from data register X to data bus. */
		DATA_X_OUT(31),
		/** Pass value from data bus to data register Y. */
		DATA_Y_IN(32),
		/** Pass value from data register Y to data bus. */
		DATA_Y_OUT(33),
		/** Pass value from data bus to I/O port register. */
		IO_PORT_IN(34),
		/** Pass value from I/O port register to data bus. */
		IO_PORT_OUT(35),
		/** Perform I/O operation. */
		STROBE_START(36),
		/** Pass value from strobe signal register to data bus. */
		STROBE_OUT(37),
		/** Pass value from data bus to logic arithmetic unit. */
		ALU_IN(38);
		
		/** Signal ID. */
		public final Integer ID;
		
		/**
		 * Constructs enum value.
		 * @param id - ID of a signal.
		 */
		private AvailableSignals(Integer id) {
			this.ID = id;
		}
	}
	
	/**
	 * List of all available extensions.
	 * @author Tomasz Rzepka
	 * @version 1.0
	 */
	/*public enum AvailableExtensions {
		BUS_CONNECTION,
		ACCUMULATOR_MODIFIERS,
		ALU_LOGICAL_OPERATIONS,
		ALU_ARITHMETIC_OPERATIONS,
		STACK_MODIFIERS,
		DATA_REGISTER_X,
		DATA_REGISTER_Y,
		INPUT_OUTPUT,
		FLAGS
	}
	
	public interface ExtensionInfo {
		default public AvailableRegisters[] getRegisters() {
			return new AvailableRegisters[] {};
		}
		
		default public AvailableSignals[] getSignals() {
			return new AvailableSignals[] {};
		}
	}
	
	public static class BusConnectionExtensionInfo implements ExtensionInfo {
		public AvailableSignals[] getSignals() {
			return new AvailableSignals[] {
				AvailableSignals.BUS_CONNECTION
			};
		}
	}
	
	public static class AccumulatorExtensionInfo implements ExtensionInfo {
		public AvailableSignals[] getSignals() {
			return new AvailableSignals[] {
				AvailableSignals.ACCUMULATOR_INCREMENT,
				AvailableSignals.ACCUMULATOR_DECREMENT
			};
		}
	}
	
	public static class StackExtensionInfo implements ExtensionInfo {
		public AvailableRegisters[] getRegisters() {
			return new AvailableRegisters[] {
				AvailableRegisters.STACK_POINTER
			};
		}
		
		public AvailableSignals[] getSignals() {
			return new AvailableSignals[] {
				AvailableSignals.STACK_POINTER_IN,
				AvailableSignals.STACK_POINTER_OUT,
				AvailableSignals.STACK_POINTER_INCREMENT,
				AvailableSignals.STACK_POINTER_DECREMENT
			};
		}
	}
	
	public static final Map<AvailableExtensions, ExtensionInfo> EXTENSION_INFOS;
	static {
		EXTENSION_INFOS = new HashMap<>();
		EXTENSION_INFOS.put(AvailableExtensions.BUS_CONNECTION, new BusConnectionExtensionInfo());
		EXTENSION_INFOS.put(AvailableExtensions.ACCUMULATOR_MODIFIERS, new AccumulatorExtensionInfo());
		EXTENSION_INFOS.put(AvailableExtensions.ALU_LOGICAL_OPERATIONS, new BusConnectionExtensionInfo());
		EXTENSION_INFOS.put(AvailableExtensions.ALU_ARITHMETIC_OPERATIONS, new BusConnectionExtensionInfo());
		EXTENSION_INFOS.put(AvailableExtensions.STACK_MODIFIERS, new StackExtensionInfo());
		EXTENSION_INFOS.put(AvailableExtensions.DATA_REGISTER_X, new BusConnectionExtensionInfo());
		EXTENSION_INFOS.put(AvailableExtensions.DATA_REGISTER_Y, new BusConnectionExtensionInfo());
		EXTENSION_INFOS.put(AvailableExtensions.INPUT_OUTPUT, new BusConnectionExtensionInfo());
		EXTENSION_INFOS.put(AvailableExtensions.FLAGS, new BusConnectionExtensionInfo());
	}*/
}