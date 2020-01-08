import React from 'react';
import ClayIcon from '@clayui/icon';
import classNames from 'classnames';
import ClayButton from '@clayui/button';

export default function SideMenu(props) {
	return (
		<ul className="nav side-menu bg-dark" role="tablist">
			{props.items.map(item => (
				<li className="nav-item" key={item.slug}>
					<ClayButton
						className={
							classNames(
								"mx-3 my-2 btn-secondary",
								props.active === item.slug && 'active'
							)
						}
						displayType="unstyled"
						monospaced
						onClick={(e) => {
							e.preventDefault();
							props.open(item.href, item.slug)
						}}
					>
						<ClayIcon symbol={item.icon} />
					</ClayButton>
				</li>
			))}
		</ul>
	);
}