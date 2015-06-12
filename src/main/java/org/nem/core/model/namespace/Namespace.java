package org.nem.core.model.namespace;

import org.nem.core.model.Address;
import org.nem.core.model.primitive.BlockHeight;

/**
 * Represents a namespace which is owned by an account.
 * The ownership is temporary and therefore has an expiry block height.
 */
public class Namespace {

	private final NamespaceId id;
	private final Address owner;
	private final BlockHeight expiryHeight;

	/**
	 * Creates a new namespace.
	 *
	 * @param id The namespace id.
	 * @param owner The owner address.
	 * @param expiryHeight The block height at which the ownership expires.
	 */
	public Namespace(
			final NamespaceId id,
			final Address owner,
			final BlockHeight expiryHeight) {
		this.id = id;
		this.owner = owner;
		this.expiryHeight = expiryHeight;
	}

	/**
	 * Gets the namespace id.
	 *
	 * @return the namespace id.
	 */
	public NamespaceId getId() {
		return  this.id;
	}

	/**
	 * Gets the owner's address of the namespace.
	 *
	 * @return The owner address.
	 */
	public Address getOwner() {
		return this.owner;
	}

	/**
	 * Gets the height at which the ownership expires.
	 *
	 * @return The height at which ownership expires.
	 */
	public BlockHeight getExpiryHeight() {
		return this.expiryHeight;
	}

	/**
	 * Returns a value indicating whether ownership has expired at a given block height.
	 *
	 * @param height The height to test.
	 * @return true if the ownership has not expired, false otherwise.
	 */
	public boolean isActive(final BlockHeight height) {
		return this.expiryHeight.compareTo(height) > 0;
	}

	// TODO 20150612 BR -> all: take owner and expiry height to check for equality as well?
	@Override
	public int hashCode() {
		return this.id.hashCode() ^ this.owner.hashCode() ^ this.expiryHeight.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null || !(obj instanceof Namespace)) {
			return false;
		}

		final Namespace rhs = (Namespace)obj;
		if (this.id.equals(rhs.id) &&
			this.owner.equals(rhs.owner) &&
			this.expiryHeight.equals(rhs.expiryHeight)) {
			return true;
		}

		return false;
	}
}
